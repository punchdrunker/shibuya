@EpoxyDataBindingLayouts({R.layout.feed_event})
@PackageModelViewConfig(rClass = R.class)
@PackageEpoxyConfig(
        requireAbstractModels = true,
        requireHashCode = true,
        implicitlyAddAutoModels = true
)
package tokyo.punchdrunker.shibuya;

import com.airbnb.epoxy.EpoxyDataBindingLayouts;
import com.airbnb.epoxy.PackageEpoxyConfig;
import com.airbnb.epoxy.PackageModelViewConfig;